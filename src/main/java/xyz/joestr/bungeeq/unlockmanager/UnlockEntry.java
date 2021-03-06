/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xyz.joestr.bungeeq.unlockmanager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import xyz.joestr.dbwrapper.DatabaseWrapable;

/**
 *
 * @author Joel
 */
public class UnlockEntry implements DatabaseWrapable {

    String target;
    String unlocker;
    LocalDateTime start;
    LocalDateTime end;
    Integer status;
    String notice;

    public UnlockEntry() {

    }

    public UnlockEntry(String target, String unlocker, LocalDateTime start, LocalDateTime end, Integer status, String notice) {
        this.target = target;
        this.unlocker = unlocker;
        this.start = start;
        this.end = end;
        this.status = status;
        this.notice = notice;
    }

    public String getTarget() {
        return target;
    }

    public String getUnlocker() {
        return unlocker;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public Integer getStatus() {
        return status;
    }

    public String getNotice() {
        return notice;
    }

    @Override
    public String databaseTableName() {
        return "bu_unlocks";
    }

    @Override
    public Collection<String> databaseColumnNames() {
        List<String> result = new ArrayList<>();
        result.add("target_uuid");
        result.add("unlocker_uuid");
        result.add("start_datetime");
        result.add("end_datetime");
        result.add("status");
        result.add("notice");
        return result;
    }

    @Override
    public Collection<String> classFieldNames() {
        List<String> result = new ArrayList<>();
        result.add("target");
        result.add("unlocker");
        result.add("start");
        result.add("end");
        result.add("status");
        result.add("notice");
        return result;
    }

}
