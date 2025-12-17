package com.ivancorrales.streams.objects;

import java.util.Comparator;
import java.util.List;
import java.lang.Comparable; // We use this to compare objects

/**
 * KATA: Streams con OBJETOS (playlist tipo Spotify)
 *
 * Normas:
 * - No uses for / while.
 * - Resuelve cada método encadenando operaciones de Stream.
 * - Piensa en receta: seleccionar → transformar → ordenar → recortar → resultado final.
 *
 * Qué vas a practicar aquí:
 * - filter(...)      : seleccionar canciones (por artista, rating, duración...)
 * - map(...)         : transformar Song -> String (título), Song -> int (seconds), Song -> double (rating)
 * - distinct()       : eliminar duplicados (depende de equals/hashCode de Song)
 * - sorted(...)      : ordenar por uno o varios criterios (Comparator)
 * - limit(n)         : quedarte con el TOP N
 * - mapToInt(...)    : pasar a IntStream y calcular sum/min/max/average
 * - mapToDouble(...) : pasar a DoubleStream y calcular average/max...
 * - findFirst/orElse : obtener “el primero” tras ordenar/filtrar, con valor por defecto
 * - collect(...)     : para construir texto (joining) o listas (si no usáis toList)
 *
 * Nota:
 * - Hay una canción repetida ("Refactor" - "Noa") a propósito para practicar distinct().
 */
public class ObjectStreamsKata {

    private final List<Song> playlist = List.of(
            new Song("Midnight Run", "Noa", 210, 4.6),
            new Song("Campus Life", "Leo", 180, 4.1),
            new Song("Refactor", "Noa", 240, 4.9),
            new Song("Bug Hunter", "Ana", 200, 3.8),
            new Song("Streams Day", "Leo", 195, 4.7),
            new Song("Refactor", "Noa", 240, 4.9), // repetida a propósito
            new Song("Clean Code", "Ana", 230, 4.2)
    );

    /**
     * NIVEL 1 (1 paso intermedio)
     * Cuenta cuántas canciones tienen rating >= 4.5.
     *
     * Requisitos:
     * - Cuenta también las repetidas si están en la lista.
     *
     * (Piensa: seleccionar → count)
     */
    public long contarRecomendadas() {
    	return playlist.stream()
                .filter(s -> s.getRating() >= 4.5)
                .count();
    }
    /**
     * LEVEL 2 (2 intermediate steps)
     * Returns the titles of the songs by a given artist.
     *
     * Requirements:
     * - Preserve the original order of the playlist.
     *
     * (Think: select by artist → transform to titles → list)
     */
    public List<String> titulosDeArtista(String artista) {
        //throw new UnsupportedOperationException("TODO");
    	return playlist.stream()

    			.filter(p -> p.getArtist().equalsIgnoreCase(artista))
    			.map(Song::getTitle)
    			.toList();    }

    /**
     * LEVEL 3 (4 intermediate steps)
     * Returns titles with rating >= 4.5:
     * - without duplicates (if a song appears more than once, it should appear only once)
     * - sorted alphabetically
     *
     * (Think: select → distinct → map(title) → sorted → list)
     */
    public List<String> titulosRecomendadosUnicosOrdenados() {
       // throw new UnsupportedOperationException("TODO");
    	return playlist.stream()
         .filter(p -> p.getRating() >= 4.5)
         .distinct()
         .map(Song::getTitle)
          .sorted()
          .toList();
    	}

    /**
     * LEVEL 4 (sorting by multiple criteria + TOP)
     * Returns the TOP 2 titles, sorting songs as follows:
     * 1) rating descending
     * 2) if tied, duration (seconds) descending
     *
     * Requirements:
     * - No duplicates (use distinct() before the TOP).
     * - Return ONLY the titles (List<String>).
     *
     * (Think: distinct → sorted(criteria) → limit(2) → map(title) → list)
     */
    public List<String> top2PorRatingLuegoDuracion() {
       // throw new UnsupportedOperationException("TODO");
    
	return playlist.stream()
.distinct()
.sorted(Comparator.comparing(Song::getRating, Comparator.reverseOrder()))
.limit(2)
.map(Song::getTitle)
.toList();
    }

    /**
     * NIVEL 5 (IntStream)
     * Devuelve la duración total (suma de seconds) de la playlist.
     *
     * Requisitos:
     * - Incluye repetidas si están en la lista.
     *
     * (Piensa: mapToInt(seconds) → sum)
     */
    public int duracionTotal() {
       // throw new UnsupportedOperationException("TODO");
    return playlist.stream()
    		.mapToInt(Song::getSeconds)
    		.sum();
    }

    /**
     * NIVEL 6 (DoubleStream + average)
     * Devuelve la media de rating de TODA la playlist.
     *
     * Requisitos:
     * - Incluye repetidas si están en la lista.
     * - Si la playlist estuviera vacía, devuelve 0.0
     *
     * (Piensa: mapToDouble(rating) → average → orElse(0.0))
     */
    public double mediaRatingPlaylist() {
       // throw new UnsupportedOperationException("TODO");
return playlist.stream()
		.mapToDouble(Song::getRating)
		.average()
		.orElse(0.0);
    }

    /**
     * NIVEL 7 (joining)
     * Devuelve un CSV de títulos con rating >= 4.0:
     * - sin repetidos
     * - ordenados por artista y luego por título
     * - formato: "t1, t2, t3"
     *
     * (Piensa: seleccionar → distinct → ordenar → map(título) → joining(", "))
     */
    public String csvTitulosBuenosOrdenados() {
        throw new UnsupportedOperationException("TODO");
    }

    // -------------------------------------------------------------------------
    // EJERCICIOS EXTRA (sin Map) - suben dificultad poco a poco
    // -------------------------------------------------------------------------

    /**
     * EXTRA 1 (match)
     * Devuelve true si existe alguna canción con duración >= 240 segundos.
     *
     * (Piensa: ¿existe alguna...? → anyMatch)
     */
    public boolean hayCancionLarga() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * EXTRA 2 (min / findFirst)
     * Devuelve el título de la canción MÁS CORTA (menor seconds).
     * Si hay empate de duración, escoge la de título alfabéticamente primero.
     * Si la playlist estuviera vacía, devuelve "N/A".
     *
     * (Piensa: ordenar por seconds asc, luego título asc → findFirst → map(título) → orElse("N/A"))
     */
    public String tituloCancionMasCorta() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * EXTRA 3 (filter + sorted + limit)
     * Devuelve los 3 títulos con rating más alto de un artista dado (sin repetidos),
     * ordenados por rating desc y luego por título asc.
     *
     * Si el artista no tiene canciones, devuelve lista vacía.
     */
    public List<String> top3DeArtistaPorRating(String artista) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * EXTRA 4 (mapToInt + max)
     * Devuelve la duración máxima (seconds) de las canciones con rating >= 4.0.
     * Si no hay ninguna, devuelve 0.
     */
    public int duracionMaximaDeBuenas() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * EXTRA 5 (map + distinct + sorted)
     * Devuelve la lista de artistas únicos, ordenados alfabéticamente.
     *
     * (Piensa: map(artista) → distinct → sorted → lista)
     */
    public List<String> artistasUnicosOrdenados() {
        throw new UnsupportedOperationException("TODO");
    }
}