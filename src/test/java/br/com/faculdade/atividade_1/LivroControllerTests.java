package br.com.faculdade.atividade_1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LivroController.class)
public class LivroControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testListarLivros() throws Exception {
        mockMvc.perform(get("/livros"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].titulo").value("Dom Casmurro"));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        mockMvc.perform(get("/livros/2"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.titulo").value("O Hobbit"));
    }

    @Test
    public void testAdicionarLivro() throws Exception {
        String novoLivro = "{\"id\":3,\"titulo\":\"1984\",\"autor\":\"George Orwell\"}";
        mockMvc.perform(post("/livros")
            .contentType(MediaType.APPLICATION_JSON)
            .content(novoLivro))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.titulo").value("1984"));
    }
}
