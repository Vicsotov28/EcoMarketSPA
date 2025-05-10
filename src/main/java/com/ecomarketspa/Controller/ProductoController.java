package com.ecomarketspa.Controller;

import com.ecomarketspa.Model.Producto;
import com.ecomarketspa.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // 1. GET: Listar todos los productos
    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // 2. GET: Buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerProductoPorId(@PathVariable Long id) {
        return productoService.obtenerProductoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. POST: Crear producto
    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    // 5. DELETE: Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        if (productoService.obtenerProductoPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}