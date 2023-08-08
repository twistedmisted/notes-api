package ua.zxc.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {

    private List<T> content = new ArrayList<>();

    private int currentPageNumber;

    private int totalPages;

    public PageDto(int currentPageNumber, int totalPages) {
        this.currentPageNumber = currentPageNumber;
        this.totalPages = totalPages;
    }

    public boolean hasContent() {
        return !this.content.isEmpty();
    }

    public void addElement(T element) {
        content.add(element);
    }
}
