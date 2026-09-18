package com.condomanager.api.controller

import com.condomanager.api.entity.Usuario
import com.condomanager.api.service.UsuarioService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("usuario")
@CrossOrigin("*")
class UsuarioController(private val usuarioService: UsuarioService) {

    @PostMapping
    fun cadastro(@RequestBody usuario: Usuario): ResponseEntity<Usuario> {

        return ResponseEntity.ok( usuarioService.cadastro(usuario))
    }

    @GetMapping
    fun listar(): ResponseEntity<List<Usuario>> {

        return ResponseEntity.ok( usuarioService.listar())
    }

    @GetMapping("/encontrar/{id}")
    fun encotrar(@PathVariable id: Long): ResponseEntity<Usuario> {
        return  ResponseEntity.ok(usuarioService.buscar(id))
    }

    @DeleteMapping("/deletar/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
            if (!usuarioService.deletar(id)) {
                return ResponseEntity.notFound().build()
            }
        return ResponseEntity.noContent().build()

    }

    @PutMapping("/atualizar/{id}")
    fun atualizar(@PathVariable id: Long, @RequestBody usuario: Usuario): ResponseEntity<Usuario> {
        val atualizado = usuarioService.atualizar(id, usuario) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(atualizado)
    }
}