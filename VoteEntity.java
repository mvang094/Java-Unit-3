package com.javaunit3.springmvc;

import javax.persistence.*;

@Entity //defines the class as a Hibernate Entity
@Table(name = "votes") //Creates new table Votes
public class VoteEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "voter_name")
    private String voterName;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getVoterName(){
        return voterName;
    }

    public void setVoterName(String voterName){
        this.voterName = voterName;
    }
}
