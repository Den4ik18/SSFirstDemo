package com.model;

import com.dateadapter.MyAdapter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.dateadapter.LocalDateDeserializer;
import com.dateadapter.LocalDateSerializer;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement(name = "jobs")
public class Job {
    private Long id;
    private String companyName;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    private String position;

    public Job() {
    }

    public Job(String companyName, LocalDate startDate, LocalDate endDate, String position) {
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
    }

    public Job(Long id, String companyName, LocalDate startDate, LocalDate endDate, String position) {
        this.id = id;
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    @XmlJavaTypeAdapter(value = MyAdapter.class)
    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    @XmlJavaTypeAdapter(value = MyAdapter.class)

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return getCompanyName() + ", " + getStartDate() + ", " + getEndDate() + ", " + getPosition();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id.equals(job.id) &&
                Objects.equals(companyName, job.companyName) &&
                Objects.equals(startDate, job.startDate) &&
                Objects.equals(endDate, job.endDate) &&
                Objects.equals(position, job.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, companyName, startDate, endDate, position);
    }

}
