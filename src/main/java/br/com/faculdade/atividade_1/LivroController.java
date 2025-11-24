package br.com.faculdade.atividade_1;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final List<Livro> livros = new ArrayList<>();

    public LivroController() {
        livros.add(new Livro(1, "Dom Casmurro", "Machado de Assis"));
        livros.add(new Livro(2, "O Hobbit", "J.R.R. Tolkien"));
    }

    @GetMapping
    public List<Livro> listarLivros() {
        return livros;
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable int id) {
        return livros.stream().filter(l -> l.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    public Livro adicionarLivro(@RequestBody Livro livro) {
        livros.add(livro);
        return livro;
    }

    static class Livro {
        private int id;
        private String titulo;
        private String autor;

        public Livro() {}
        public Livro(int id, String titulo, String autor) {
            this.id = id;
            this.titulo = titulo;
            this.autor = autor;
        }
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }
    }
}
