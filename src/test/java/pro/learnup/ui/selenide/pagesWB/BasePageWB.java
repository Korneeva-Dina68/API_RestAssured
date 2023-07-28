package pro.learnup.ui.selenide.pagesWB;

import lombok.Getter;
import pro.learnup.ui.selenide.pagesWB.blocksWB.HeaderBlockWB;

public class BasePageWB {
    @Getter
    private HeaderBlockWB headerBlockWB = new HeaderBlockWB();

}
