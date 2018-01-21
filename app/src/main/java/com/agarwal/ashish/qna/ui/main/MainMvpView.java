package com.agarwal.ashish.qna.ui.main;

import java.util.List;

import com.agarwal.ashish.qna.data.model.Ribot;
import com.agarwal.ashish.qna.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
