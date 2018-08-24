package com.restaurantic.producto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/api/v1/productos")
public class ProductoController {

    private ProductoServiceImpl productoService;

    public ProductoController(ProductoServiceImpl productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{codigo}")
    public Producto findByCodigo(@PathVariable String codigo){
        return this.findByCodigo(codigo);
    }

    @GetMapping
    public List<Producto> find(@RequestParam(required = false) String nombre) {
        if(nombre == null) {
            return this.productoService.findAll();
        }
        return this.productoService.filterByNombre(nombre);

    }

    @PostMapping
    public Producto create(@RequestBody Producto producto) {
        return this.productoService.create(producto);
    }

    @PutMapping
    public void update(String codigo, Producto producto){
        this.productoService.update(codigo, producto);
    }

    @DeleteMapping
    public void delete(String codigo){
        this.productoService.delete(codigo);
    }
}
