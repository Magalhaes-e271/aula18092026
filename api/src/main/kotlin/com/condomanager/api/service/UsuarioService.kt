package com.condomanager.api.service

import com.condomanager.api.entity.Usuario
import com.condomanager.api.repository.UsuarioRepository
import org.springframework.stereotype.Service


@Service
class UsuarioService(private val usuarioRepository: UsuarioRepository) {

    fun cadastro(usuario: Usuario): Usuario {
        return usuarioRepository.save(usuario)
    }

    fun listar(): List<Usuario> {
        return usuarioRepository.findAll();
    }

    fun buscar(id: Long): Usuario {
        return usuarioRepository.findById(id).orElseThrow(){ Exception("Usuario não encontrado") }
    }

    fun deletar(id: Long): Boolean {
        if (!usuarioRepository.existsById(id)) {
            return false
        }
        usuarioRepository.deleteById(id)
        return true

    }
    fun atualizar(id: Long, usuario: Usuario) : Usuario? {
        var usuarioExestente = usuarioRepository.findById(id).orElse(null) ?: return null
        usuarioExestente.email = usuario.email
        usuarioExestente.senha = usuario.senha
        return usuarioRepository.save(usuarioExestente)
    }

}
