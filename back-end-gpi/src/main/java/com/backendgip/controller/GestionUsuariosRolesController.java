package com.backendgip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendgip.security.models.ItemRol;
import com.backendgip.security.models.RolSeg;
import com.backendgip.security.models.SubItemRol;
import com.backendgip.security.models.SubLevelItemRol;
import com.backendgip.security.models.Submenu;
import com.backendgip.security.models.Usuario;
import com.backendgip.security.services.ArmaMenuRol;
import com.backendgip.security.services.IRolService;
import com.backendgip.security.services.ISubmenuService;
import com.backendgip.security.services.IUsuarioService;

@RestController
@RequestMapping("/api/gestionUsuariosRoles")
public class GestionUsuariosRolesController {

	@Autowired
	IUsuarioService usuarioService;

	@Autowired
	IRolService iRolService;

	@Autowired
	ISubmenuService submenuService;

	@Autowired
	ArmaMenuRol armaMenuRolService;

	@PostMapping("/crearRol")
	public ResponseEntity<?> crearRol(@RequestBody RolSeg rol) {

		RolSeg rolNuevo = null;
		Map<String, Object> response = new HashMap<>();

		try {
			rolNuevo = iRolService.findByRolNombre(rol.getRolNombre());
			if (rolNuevo != null && rolNuevo.getRolId() != 0 && rol.getRolId() == null) {
				response.put("error", "Este Rol ya se encuentra registrado");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			if (rolNuevo != null && rolNuevo.getRolId() != 0) {
				armaMenuRolService.eliminarOpciones(rolNuevo);
			}
			rol.getSubmenuRoles().forEach(subMenuRol -> {
				subMenuRol.setRol(rol);
				subMenuRol.setItemRol(new ArrayList<ItemRol>());
				subMenuRol.getSubmenu().getItems().forEach(Item -> {
					ItemRol itemRol = new ItemRol();
					itemRol.setItem(Item);
					itemRol.setSubmenuRol(subMenuRol);
					subMenuRol.getItemRol().add(itemRol);
					itemRol.setSubItemRol(new ArrayList<>());
					itemRol.getItem().getSubItems().forEach(subItem -> {
						SubItemRol subItemRol = new SubItemRol();
						subItemRol.setSubItem(subItem);
						subItemRol.setItemRol(itemRol);
						subItemRol.setSubLevelItemRols(new ArrayList<>());
						itemRol.getSubItemRol().add(subItemRol);
						subItemRol.getSubItem().getSubLevelItems().forEach(subLevel -> {
							SubLevelItemRol subLevelItemRol = new SubLevelItemRol();
							subLevelItemRol.setSubLevelItem(subLevel);
							subLevelItemRol.setSubItemRol(subItemRol);
							subItemRol.getSubLevelItemRols().add(subLevelItemRol);
						});
					});
				});
			});

			rolNuevo = iRolService.guardarRol(rol);

		} catch (Exception e) {
			e.printStackTrace();
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido creado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@GetMapping("/empleadorol/{idEmpleado}")
	public ResponseEntity<?> obtenerRolUsuario(@PathVariable Integer idEmpleado) {
		RolSeg rol = new RolSeg();
		try {
			Usuario usuario = usuarioService.buscaPorEmpleadoAsociado(idEmpleado);
			rol.setRolNombre(usuario.getUsuarioRoles().get(0).getRol().getRolNombre());
			rol.setRolDescripcion(usuario.getUsuarioRoles().get(0).getRol().getRolDescripcion());
			rol.setSubmenuRoles(null);
			rol.setUsuarioRoles(null);
	        return ResponseEntity.ok(rol);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el rol del usuario");
	    }
	}

	@GetMapping("/listarRoles")
public ResponseEntity<?> listarRoles() {
    try {
        List<RolSeg> roles = iRolService.listarRoles();

        roles.forEach(rol -> {
            if (rol.getSubmenuRoles() != null) {
                rol.getSubmenuRoles().forEach(submenu -> {
                    if (submenu.getItemRol() != null) {
                        submenu.getItemRol().forEach(itemRol -> {
                            if (itemRol.getSubItemRol() != null) {
                                itemRol.getSubItemRol().forEach(subItemRol -> {
                                    if (subItemRol.getSubLevelItemRols() != null) {
                                        subItemRol.getSubLevelItemRols().forEach(subLevelItemRol -> {
                                            subLevelItemRol.setSubItemRol(null);
                                        });
                                    }
                                    subItemRol.setItemRol(null);
                                });
                            }
                            itemRol.setSubmenuRol(null);
                        });
                    }
                    submenu.setRol(null);
                });
            }
            rol.setUsuarioRoles(null);
        });

        return ResponseEntity.ok(roles);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener la lista de roles: " + e.getMessage());
    }
}


	@GetMapping("/listarMenu")
	public ResponseEntity<?> listarMenu() {

		try {
			List<Submenu> submenu = submenuService.Listar();

			submenu.forEach(sub -> {

				if (sub.getLabel().equalsIgnoreCase("INICIO")) {
					sub.setSeleccionado(true);
				}

				sub.getItems().forEach(item -> {
					item.getSubItems().forEach(subitem -> {
						subitem.getSubLevelItems().forEach(sublevelitem -> {
							sublevelitem.setSubItem(null);
						});
						subitem.setItem(null);
					});
					item.setSubmenu(null);
				});

			});

			return ResponseEntity.ok(submenu);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al obtener las opciones de menu");
		}

	}

}
