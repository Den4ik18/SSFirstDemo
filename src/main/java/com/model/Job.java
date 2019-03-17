package com.model;

import com.dateadapter.MyAdapter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.dateadapter.LocalDateDeserializer;
import com.dateadapter.LocalDateSerializer;

import javax.validation.constraints.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement
public class Job {
    @NotNull(message = "id must be not null")
    @PositiveOrZero(message = "id must be positive or zero")
    private Long id;

    @NotNull(message = "company name must be not null")
    @NotBlank(message = "company name can't be blank")
    private String companyName;

    @NotNull(message = "start date of work must be not null")
    @Past(message = "start date of work must be in past")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate startDate;

    @NotNull(message = "end date of work must be not null")
    @PastOrPresent(message = "end date of work must be in past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate endDate;

    @NotNull(message = "position must be not null")
    @NotBlank(message = "position can't be blank")
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
        return id==(job.id) &&
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
