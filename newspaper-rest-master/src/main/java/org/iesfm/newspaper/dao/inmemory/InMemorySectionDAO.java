package org.iesfm.newspaper.dao.inmemory;

import org.iesfm.newspaper.dao.SectionDAO;
import org.iesfm.newspaper.entity.Section;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InMemorySectionDAO implements SectionDAO {
    private Map<Integer, Section> sections = new HashMap<>();


    @Override
    public List<Section> list() {
        return new LinkedList<>(sections.values());
    }

    @Override
    public Section getSection(int id) {
        return sections.get(id);
    }

    @Override
    public boolean add(Section section) {
        return sections.putIfAbsent(section.getId(), section) == null;
    }

    @Override
    public boolean delete(int id) {
        return sections.remove(id) != null;
    }

    @Override
    public boolean update(int id, Section section) {
        if (sections.containsKey(id)) {
            sections.remove(id);
            sections.put(section.getId(), section);
            return true;
        } else {
            return false;
        }
    }
}
