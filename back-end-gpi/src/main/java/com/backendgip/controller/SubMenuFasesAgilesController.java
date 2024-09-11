package com.backendgip.controller;

import com.backendgip.model.FasesAgiles;
import com.backendgip.model.LogSistema;
import com.backendgip.model.SubMenuFasesAgiles;
import com.backendgip.service.SubMenuFasesAgilesService;
import com.backendgip.service.LogSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SubMenuFasesAgilesController {

    @Autowired
    private SubMenuFasesAgilesService subMenuFasesAgilesService;
    
    @Autowired
    private LogSistemaService logService;

    @GetMapping("/submenus")
    public ResponseEntity<?> getAllSubMenus() {
        List<SubMenuFasesAgiles> subMenus = subMenuFasesAgilesService.findAll();
        if (subMenus != null && !subMenus.isEmpty()) {
            return ResponseEntity.ok(subMenus);
        } else {
            return ResponseEntity.badRequest().body("No se encontraron submenús.");
        }
    }

    @GetMapping("/submenus/{id}")
    public ResponseEntity<?> getSubMenuById(@PathVariable Integer id) {
        SubMenuFasesAgiles subMenu = subMenuFasesAgilesService.findById(id);
        if (subMenu != null) {
            return ResponseEntity.ok(subMenu);
        } else {
            return ResponseEntity.badRequest().body("No se encontró el submenú con ID: " + id);
        }
    }

    @PostMapping("/crear-submenu")
    public ResponseEntity<?> createSubMenu(@RequestBody SubMenuFasesAgiles subMenuFasesAgiles) {
        if (subMenuFasesAgiles.getNombre() != null && subMenuFasesAgiles.getFasesAgiles() != null) {
            SubMenuFasesAgiles createdSubMenu = subMenuFasesAgilesService.save(subMenuFasesAgiles);
            LogSistema log = new LogSistema();
            log.setAccion("CREAR");
            log.setFechaHora(new Date());
            log.setTabla(SubMenuFasesAgiles.class.toString());
            log.setIdAccion(createdSubMenu.getId());
            log.setDescripcion("Submenú creado: " + createdSubMenu.toString());
            logService.saveLog(log);
            return ResponseEntity.ok(createdSubMenu);
        } else {
            return ResponseEntity.badRequest().body("El nombre y la fase ágil son obligatorios.");
        }
    }

    @PutMapping("/editar-submenu")
    public ResponseEntity<?> updateSubMenu(@RequestBody SubMenuFasesAgiles subMenuFasesAgiles) {
        if (subMenuFasesAgiles.getId() != null && subMenuFasesAgiles.getNombre() != null) {
            SubMenuFasesAgiles existingSubMenu = subMenuFasesAgilesService.findById(subMenuFasesAgiles.getId());
            if (existingSubMenu != null) {
                existingSubMenu.setNombre(subMenuFasesAgiles.getNombre());
                existingSubMenu.setFasesAgiles(subMenuFasesAgiles.getFasesAgiles());
                SubMenuFasesAgiles updatedSubMenu = subMenuFasesAgilesService.save(existingSubMenu);

                LogSistema log = new LogSistema();
                log.setAccion("ACTUALIZAR");
                log.setFechaHora(new Date());
                log.setTabla(SubMenuFasesAgiles.class.toString());
                log.setIdAccion(updatedSubMenu.getId());
                log.setDescripcion("Submenú actualizado: " + updatedSubMenu.toString());
                logService.saveLog(log);

                return ResponseEntity.ok(updatedSubMenu);
            } else {
                return ResponseEntity.badRequest().body("No se encontró el submenú con ID: " + subMenuFasesAgiles.getId());
            }
        } else {
            return ResponseEntity.badRequest().body("El ID y el nombre del submenú son obligatorios.");
        }
    }

    @PutMapping("/{id}/nombre")
    public ResponseEntity<?> updateNombre(@PathVariable Integer id, @RequestBody String nuevoNombre) {
        SubMenuFasesAgiles subMenu = subMenuFasesAgilesService.findById(id);
        if (subMenu != null && nuevoNombre != null && !nuevoNombre.isEmpty()) {
            subMenu.setNombre(nuevoNombre);
            SubMenuFasesAgiles updatedSubMenu = subMenuFasesAgilesService.save(subMenu);
            LogSistema log = new LogSistema();
            log.setAccion("ACTUALIZAR NOMBRE");
            log.setFechaHora(new Date());
            log.setTabla(SubMenuFasesAgiles.class.toString());
            log.setIdAccion(updatedSubMenu.getId());
            log.setDescripcion("Nombre del submenú actualizado: " + updatedSubMenu.toString());
            logService.saveLog(log);

            return ResponseEntity.ok(updatedSubMenu);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre es obligatorio y no debe estar vacío.");
        }
    }
/* 
    @DeleteMapping("/eliminar-submenu/{id}")
    public ResponseEntity<?> eliminarSubMenu(@PathVariable Integer id) {
        SubMenuFasesAgiles subMenu = subMenuFasesAgilesService.findById(id);
        if (subMenu != null) {
            subMenuFasesAgilesService.deleteById(id);

            LogSistema log = new LogSistema();
            log.setAccion("ELIMINAR");
            log.setFechaHora(new Date());
            log.setTabla(SubMenuFasesAgiles.class.toString());
            log.setIdAccion(id);
            log.setDescripcion("Submenú eliminado con ID: " + id);
            logService.saveLog(log);

            return ResponseEntity.ok("Submenú eliminado exitosamente.");
        } else {
                return ResponseEntity.badRequest().body("No se encontró el submenú con ID: " + id);
            }
        }  */
        @DeleteMapping("/eliminar-submenu/{id}")
public ResponseEntity<?> deleteSubMenu(@PathVariable Integer id) {
    try {
        subMenuFasesAgilesService.deleteById(id);
        return ResponseEntity.noContent().build(); 
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la sub-fase");
    }
}

    }

