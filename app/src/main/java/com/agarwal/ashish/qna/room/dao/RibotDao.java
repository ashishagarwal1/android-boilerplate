package com.agarwal.ashish.qna.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.agarwal.ashish.qna.room.entities.RibotProfile;
import com.agarwal.ashish.qna.room.constants.RivotDbConstants;

import java.util.Collection;
import java.util.List;

/**
 * Created by ashishaggarwal on 29/01/18.
 */

@Dao
public interface RibotDao {

    @Insert
    void insert(Collection<RibotProfile> ribotProfiles);

    @Query("SELECT * FROM " + RivotDbConstants.TABLE_NAME)
    public List<RibotProfile> getRibots();

    @Query("DELETE FROM " + RivotDbConstants.TABLE_NAME)
    public int deleteAll();

}
