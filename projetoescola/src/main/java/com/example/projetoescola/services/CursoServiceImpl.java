package com.example.projetoescola.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.projetoescola.dtos.CategoriaCursoDTO;
import com.example.projetoescola.dtos.CursoDTO;
import com.example.projetoescola.dtos.DadosCursoDTO;
import com.example.projetoescola.exceptions.RegraNegocioException;
import com.example.projetoescola.models.CategoriaCurso;
import com.example.projetoescola.models.Curso;
import com.example.projetoescola.repositories.CategoriaCursoRepository;
import com.example.projetoescola.repositories.CursoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CategoriaCursoRepository categoriaCursoRepository;

    @Override
    @Transactional
    public Curso salvar(CursoDTO cursoDTO) {
        CategoriaCurso categ = categoriaCursoRepository.findById(cursoDTO.getCategoriaCursoId())
                .orElseThrow(
                        () -> new RegraNegocioException("Categoria não encontrada"));

        Curso curso = new Curso();
        curso.setId(cursoDTO.getId());
        curso.setNome(cursoDTO.getNome());
        curso.setCargaHoraria(cursoDTO.getCargaHoraria());
        curso.setCategoriaCurso(categ);

        return cursoRepository.save(curso);
    }

    public List<DadosCursoDTO> listarTodos() {
        return cursoRepository.findAll().stream().map((Curso c) -> {
        return DadosCursoDTO.builder()
        .id(c.getId())
        .nome(c.getNome())
        .cargaHoraria(c.getCargaHoraria())
        .categoria(CategoriaCursoDTO.builder()
        .id(c.getCategoriaCurso().getId())
        .nome(c.getCategoriaCurso().getNome())
        .build())
        .build();
        }).collect(Collectors.toList());
        
}
    @Override
    public DadosCursoDTO obterPorId(Long id) {
    return cursoRepository.findById(id).map((Curso c) -> {
    return DadosCursoDTO
    .builder()
    .id(c.getId())
    .nome(c.getNome())
    .cargaHoraria(c.getCargaHoraria())
    .categoria(CategoriaCursoDTO.builder()
    .id(c.getCategoriaCurso().getId())
    .nome(c.getCategoriaCurso().getNome()
    ).build()
    ).build();
    }).orElseThrow(() -> new RegraNegocioException("Curso não encontrado com o ID fornecido"));
    }
    

    @Override
    @Transactional
    public void excluir(Long id) {
    cursoRepository.deleteById(id);

    }

    @Override
    public void editar(Long id, CursoDTO dto) {
    Curso curso = cursoRepository.findById(id)
    .orElseThrow(() -> new RegraNegocioException("Códigou suário não encontrado."));
    CategoriaCurso categoriaCurso =
    categoriaCursoRepository.findById(dto.getCategoriaCursoId())
    .orElseThrow(() -> new RegraNegocioException("Categoria não encontrado."));
    curso.setNome(dto.getNome());
    curso.setCargaHoraria(dto.getCargaHoraria());
    curso.setCategoriaCurso(categoriaCurso);
    cursoRepository.save(curso);
}

}