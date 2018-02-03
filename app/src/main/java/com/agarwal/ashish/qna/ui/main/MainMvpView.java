package com.agarwal.ashish.qna.ui.main;

import java.util.List;

import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<RibotProfile> ribotProfiles);

    void showRibotsEmpty();

    void showError();

}
