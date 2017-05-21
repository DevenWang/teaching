package com.whut.teaching.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by wpc on 2017/5/19.
 */
@Entity
public class Institute {

    @Id
    @Column(length = 10)
    private String instituteId;

    @Column(length = 40)
    private String name;

    public String getInstituteId() {
        return instituteId;
    }

    public void setInstituteId(String instituteId) {
        this.instituteId = instituteId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
