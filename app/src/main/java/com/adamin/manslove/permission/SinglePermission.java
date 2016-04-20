package com.adamin.manslove.permission;

/**
 * Created by adamlee on 2016/4/17.
 */
public class SinglePermission {

    private String mPermissionName;
    private boolean mRationalNeeded = false;
    private String mReason;

    public SinglePermission(String permissionName) {
        mPermissionName = permissionName;
    }

    public SinglePermission(String permissionName, String reason) {
        mPermissionName = permissionName;
        mReason = reason;
    }

    public boolean isRationalNeeded() {
        return mRationalNeeded;
    }

    public void setRationalNeeded(boolean rationalNeeded) {
        mRationalNeeded = rationalNeeded;
    }

    public String getReason() {
        return mReason == null ? "" : mReason;
    }

    public void setReason(String reason) {
        mReason = reason;
    }

    public String getPermissionName() {
        return mPermissionName;
    }

    public void setPermissionName(String permissionName) {
        mPermissionName = permissionName;
    }


}
