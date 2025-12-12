package com.ivancorrales.streams.strings;

import java.util.List;

/**
 * KATA: Streams con Strings (nivel progresivo, SIN flatMap)
 *
 * Normas:
 * - No uses for / while.
 * - Resuelve cada método encadenando operaciones de Stream.
 * - Lee el enunciado como si fuera una “receta”: seleccionar → transformar → ordenar → resultado final.
 *
 * Operaciones que vas a usar:
 * - Seleccionar elementos: filter(...)
 * - Transformar elementos: map(...)
 * - Quitar repetidos: distinct()
 * - Ordenar: sorted()
 * - Pasar a números: mapToInt(...)
 *
 * Nota:
 * - En esta versión NO extraemos hashtags de frases con split/flatMap.
 *   En su lugar trabajamos con una lista "tokens" ya preparada.
 */
public class StringStreamsKata {

    private final List<String> paises = List.of("Italia", "España", "Portugal", "Irlanda", "Noruega", "Francia");
    private final List<String> usuarios = List.of("  noa  ", "LEO", "ana", "Noa", "   Leo ", "mario", "ANA");

    private final List<String> tokens = List.of(
            "Hoy", "toca", "#java", "y", "#streams",
            "Me", "flipa", "#dam", "#java",
            "Probando", "#Streams", "#JAVA", "#java",
            "sin", "hashtags", "aquí",
            "#dam", "#fp", "#java"
    );
    private final List<List<String>> comentariosPorPost = List.of(
            List.of("Brutal", "Me sirve", "  "),
            List.of("Buenísimo", "me sirve", "Gracias!"),
            List.of("brutal", "TOP", ""),
            List.of("Sin comentarios", "  ", "Gracias!")
    );

    private final List<String> hashtagsPorPost = List.of(
            "#java,#streams",
            "#dam,#java",
            "#streams,#JAVA,#java",
            "",
            "#fp,#dam,#java"
    );

    /**
     * NIVEL 1 (1 paso intermedio)
     * Devuelve los países cuyo nombre termina en "a".
     *
     * Requisitos:
     * - Conserva el orden original de la lista.
     */
    public List<String> paisesQueTerminanEnA() {
    	return paises.stream()
                .filter(p -> p.endsWith("a"))
                .sorted()
                .toList();
    }

    /**
     * NIVEL 2 (2 pasos intermedios)
     * Devuelve los países cuyo nombre termina en "a" y después ordénalos alfabéticamente.
     *
     * Requisitos:
     * - El resultado debe estar ordenado (A→Z).
     */
    public List<String> paisesQueTerminanEnAOrdenados() {
        //throw new UnsupportedOperationException("TODO");
    	return paises.stream()
    			.distinct()
    			.map(String::toLowerCase)
    			.sorted()
                .toList();     
    }

    /**
     * NIVEL 3 (3 pasos intermedios)
     * Devuelve TODOS los países:
     * - convertidos a minúsculas
     * - sin repetidos
     * - ordenados alfabéticamente
     */
    public List<String> paisesMinusculasUnicosOrdenados() {
       // throw new UnsupportedOperationException("TODO");
        return paises.stream()
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .toList();
        		
        
    }

    /**
     * NIVEL 4 (3-4 pasos intermedios)
     * Devuelve usuarios “limpios” y sin duplicados.
     *
     * Normalización (en este orden):
     * 1) trim(): elimina espacios al principio y final
     * 2) toLowerCase(): pasa a minúsculas
     *
     * Eliminación de duplicados:
     * - Elimina repetidos DESPUÉS de normalizar.
     *
     * Orden:
     * - Mantén el orden de primera aparición tras normalizar.
     */
    public List<String> usuariosNormalizadosSinRepetir() {
       // throw new UnsupportedOperationException("TODO");
        return usuarios.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .toList();
    }

    /**
     * LEVEL 5 (4+ intermediate steps, WITHOUT flatMap)
     * Returns the list of unique hashtags from the "tokens" list.
     *
     * Definition of hashtag:
     * - Some has hashtag some not so put for all 
     *
     * Requirements for the result:
     * - trim spaces (trim) just in case
     * - lowercase
     * - no duplicates
     * - sorted alphabetically
     *
     * Hint:
     * - Here you DO NOT need split or flatMap: you already work with individual words.
     */
    public List<String> hashtagsUnicosOrdenados() {
        //throw new UnsupportedOperationException("TODO");
        return tokens.stream()
                .map(String::trim)
                .filter(t-> t.startsWith("#"))
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .toList();
    }

    /**
     * LEVEL 6 (conversion to IntStream)
     * Returns the total sum of letters of the countries that end with "a".
     *
     * Requirements:
     * - Before counting letters, convert the country to lowercase.
     * - Count letters with length().
     * - Convert to IntStream with mapToInt(...) and finish with sum().
     */
    public int sumaLetrasPaisesEnA() {
        throw new UnsupportedOperationException("TODO");
        return paises.stream()
        		.filter(p-> p.endsWith("a"))
        		.map(String::toLowerCase)
        		.mapToInt(String::length)
        		
        		.sum();
        		
    }

    /**
     * LEVEL 7 (IntStream + average)
     * Returns the average (double) length of the unique hashtags.
     *
     * Requirements:
     * - Use unique hashtags (same criteria as in hashtagsUnicosOrdenados()).
     * - Convert to IntStream with mapToInt(String::length).
     * - Use average().
     * - If there are no hashtags, return 0.0.
     */
    public double mediaLongitudHashtagsUnicos() {
       // throw new UnsupportedOperationException("TODO");
    }

    /**
     * LEVEL 8 (IntStream + max)
     * Returns the length of the longest hashtag (among the unique hashtags).
     *
     * Requirements:
     * - Use unique hashtags (same criteria as before).
     * - Convert to IntStream with mapToInt(String::length).
     * - Use max().
     * - If there are no hashtags, return 0.
     */
    public int longitudMaximaHashtag() {
        throw new UnsupportedOperationException("TODO");
    }
    
    /**
     * NIVEL 9 (primer flatMap “visual”)
     * Devuelve una lista con TODOS los comentarios en una sola lista.
     *
     * Requisitos del resultado:
     * - Aplana (une) todas las listas de comentarios en una única lista
     * - trim() a cada comentario
     * - quédate solo con los no vacíos
     * - pasa todo a minúsculas
     * - elimina duplicados
     * - ordena alfabéticamente
     *
     * Pista:
     * - Empiezas con List<List<String>> (comentariosPorPost)
     * - Necesitas flatMap para convertirlo en Stream<String>
     */
    public List<String> comentariosUnicosLimpiosOrdenados() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * NIVEL 10 (flatMap + split sencillo)
     * A partir de la lista hashtagsPorPost (cada string contiene hashtags separados por comas),
     * devuelve TODOS los hashtags únicos.
     *
     * Requisitos del resultado:
     * - Cada elemento puede traer "varios hashtags" separados por coma
     * - Convierte todo a minúsculas
     * - Quita espacios si los hubiera (trim)
     * - Elimina elementos vacíos
     * - Sin repetidos
     * - Ordenados alfabéticamente
     *
     * Pista:
     * - Para cada string, usa split(",") para obtener un array de hashtags
     * - Luego une todos esos arrays en un único stream (flatMap)
     */
    public List<String> hashtagsUnicosDesdeComas() {
        throw new UnsupportedOperationException("TODO");
    }
}