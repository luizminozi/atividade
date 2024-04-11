package com.example.projetoescola.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.DadosCursoDTO;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.services.CursoService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("api/curso")
public class CursoController {
    private CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Curso salvar(@RequestBody CursoDTO curso) {
        return cursoService.salvar(curso);
    }

    @GetMapping()
    public List<DadosCursoDTO> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("{id}")
    public DadosCursoDTO ObterPorId(@PathVariable Long id) {
        return cursoService.obterPorId(id);
}

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
    cursoService.excluir(id);
}

    @PutMapping("{id}")
    public void edit(@PathVariable Long id, @RequestBody CursoDTO
    dto) {
        cursoService.editar(id, dto);
}


}
