package site.yuanzhou.entity;

import java.util.Objects;

public class GotifyDTO extends NoticeDTO {
    private Integer priority;

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        // json
        return "{\"title\":\"" + getTitle() + "\",\"message\":\"" + getMessage() + "\",\"priority\":" + priority + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GotifyDTO gotifyDTO = (GotifyDTO) o;
        return Objects.equals(priority, gotifyDTO.priority);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), priority);
    }
}
