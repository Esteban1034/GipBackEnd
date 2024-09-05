package com.backendgip.imp;

import com.backendgip.model.FasesAgiles;
import com.backendgip.model.SubMenuFasesAgiles;
import com.backendgip.repository.SubMenuFasesAgilesRepository;
import com.backendgip.service.SubMenuFasesAgilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubMenuFasesAgilesServiceImp implements SubMenuFasesAgilesService {

    @Autowired
    private SubMenuFasesAgilesRepository subMenuFasesAgilesRepository;

    @Override
    public SubMenuFasesAgiles save(SubMenuFasesAgiles subMenuFasesAgiles) {
        if (subMenuFasesAgiles == null) {
            throw new IllegalArgumentException("El submenú no puede ser nulo");
        }
        return subMenuFasesAgilesRepository.save(subMenuFasesAgiles);
    }

    @Override
    public SubMenuFasesAgiles findById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return subMenuFasesAgilesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el submenú con ID: " + id));
    }

    @Override
    public List<SubMenuFasesAgiles> findAll() {
        List<SubMenuFasesAgiles> subMenus = new ArrayList<>();
        subMenuFasesAgilesRepository.findAll().forEach(subMenus::add);
        if (subMenus.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron submenús");
        }
        return subMenus;
    }

    @Override
    public List<SubMenuFasesAgiles> findByFasesAgilesId(Integer fasesAgilesId) {
        if (fasesAgilesId == null) {
            throw new IllegalArgumentException("El ID de la fase ágil no puede ser nulo");
        }
        List<SubMenuFasesAgiles> subMenus = subMenuFasesAgilesRepository.findByFasesAgiles_Id(fasesAgilesId);
        if (subMenus.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron submenús para la fase ágil con ID: " + fasesAgilesId);
        }
        return subMenus;
    }

    @Override
    public SubMenuFasesAgiles findByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        return subMenuFasesAgilesRepository.findByNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un submenú con el nombre: " + nombre));
    }

    @Override
    public void deleteById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        if (!subMenuFasesAgilesRepository.existsById(id)) {
            throw new EntityNotFoundException("No se encontró el submenú con ID: " + id);
        }
        subMenuFasesAgilesRepository.deleteById(id);
    }

    @Override
    public List<SubMenuFasesAgiles> findByNombreAndFasesAgiles(String nombre, FasesAgiles fasesAgiles) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (fasesAgiles == null) {
            throw new IllegalArgumentException("La fase ágil no puede ser nula");
        }
        List<SubMenuFasesAgiles> subMenus = subMenuFasesAgilesRepository.findByNombreAndFasesAgiles(nombre, fasesAgiles);
        if (subMenus.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron submenús con el nombre y fase ágil especificados");
        }
        return subMenus;
    }

    @Override
    public void deleteByNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío");
        }
        if (!subMenuFasesAgilesRepository.existsByNombre(nombre)) {
            throw new EntityNotFoundException("No se encontró un submenú con el nombre: " + nombre);
        }
        subMenuFasesAgilesRepository.deleteByNombre(nombre);
    }

    @Override
    public SubMenuFasesAgiles updateNombre(Integer id, String nuevoNombre) {
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nuevo nombre no puede ser nulo o vacío");
        }
        Optional<SubMenuFasesAgiles> optionalSubMenu = subMenuFasesAgilesRepository.findById(id);
        if (optionalSubMenu.isPresent()) {
            SubMenuFasesAgiles subMenu = optionalSubMenu.get();
            subMenu.setNombre(nuevoNombre);
            return subMenuFasesAgilesRepository.save(subMenu);
        } else {
            throw new EntityNotFoundException("No se encontró el submenú con ID: " + id);
        }
    }
}

