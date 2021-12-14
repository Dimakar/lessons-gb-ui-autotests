package ru.gb.lesson.lesson8;

import lombok.Getter;
import ru.gb.lesson.lesson8.blocks.HeaderBlock;

public class BasePage {
    @Getter
    private HeaderBlock headerBlock = new HeaderBlock();
}
