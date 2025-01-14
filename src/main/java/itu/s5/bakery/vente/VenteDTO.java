package itu.s5.bakery.vente;

public record VenteDTO(
        Long id,
        String nom,
        Integer idCategorie,
        Integer idGarniture
) {}
