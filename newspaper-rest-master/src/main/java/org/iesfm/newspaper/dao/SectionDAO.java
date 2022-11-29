package org.iesfm.newspaper.dao;

import org.iesfm.newspaper.entity.Section;

import java.util.List;

public interface SectionDAO {

    /**
     * Lista los secciones.
     * @return
     */
    List<Section> list();

    /**
     * Devuelve una sección
     * @param id
     * @return
     */
    Section  getSection(int id);

    /**
     * Guarda una nueva sección. Si ya existía la sección devuelve false
     * @param section
     * @return
     */
    boolean add(Section section);

    /**
     * Elimina una sección. Si no existía la sección devuelve false
     * @param id
     * @return
     */
    boolean delete(int id);

    /**
     * Actualiza la sección. Si no existía una sección con el id proporcionado devuelve false.
     * @param id
     * @param section
     * @return
     */
    boolean update(int id, Section section);

}
