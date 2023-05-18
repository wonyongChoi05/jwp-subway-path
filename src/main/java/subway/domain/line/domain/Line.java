package subway.domain.line.domain;

import subway.domain.section.domain.Section;
import subway.domain.section.domain.Sections;
import subway.domain.station.domain.Station;
import subway.domain.vo.Color;
import subway.domain.vo.Name;

import java.util.List;

public class Line {

    private final Long id;
    private final Sections sections;
    private Name name;
    private Color color;

    private Line(final Long id, final Sections sections, final Name name, final Color color) {
        this.id = id;
        this.sections = sections;
        this.name = name;
        this.color = color;
    }

    public static Line of(final Long id, final Sections sections, final Name name, final Color color) {
        return new Line(id, sections, name, color);
    }

    public static Line of(final String name, final String color) {
        return new Line(null, null, Name.from(name), Color.from(color));
    }

    public List<Station> getSortedStations(final Station finalUpStation) {
        return sections.getStationsAsc(finalUpStation);
    }

    public void updateInfo(final String name, final String color) {
        this.name = Name.from(name);
        this.color = Color.from(color);
    }


    public Long getId() {
        return id;
    }

    public String getNameValue() {
        return name.getValue();
    }

    public String getColorValue() {
        return color.getValue();
    }

    public List<Section> getSectionsValues() {
        return sections.getSections();
    }
}
