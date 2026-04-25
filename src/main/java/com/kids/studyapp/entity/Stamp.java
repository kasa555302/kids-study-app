package com.kids.studyapp.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stamps")
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate earnedDate;

    /** スタンプ絵文字（⭐🌈🎉など） */
    private String stampEmoji;

    /** スタンプ獲得理由（例: "30分勉強した！"） */
    private String reason;

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getEarnedDate() { return earnedDate; }
    public void setEarnedDate(LocalDate earnedDate) { this.earnedDate = earnedDate; }

    public String getStampEmoji() { return stampEmoji; }
    public void setStampEmoji(String stampEmoji) { this.stampEmoji = stampEmoji; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
