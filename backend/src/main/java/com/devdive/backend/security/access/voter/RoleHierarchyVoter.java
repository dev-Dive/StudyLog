package com.devdive.backend.security.access.voter;

import com.devdive.backend.security.access.AccessDecisionVoter;
import com.devdive.backend.security.core.Authentication;

import java.util.List;

public class RoleHierarchyVoter implements AccessDecisionVoter {
    private String hierarchy;
    private int hierarchyLen;

    public RoleHierarchyVoter(String hierarchy) {
        this.hierarchy = hierarchy;

        String[] hierarchyOrderList = hierarchy.split(">");
        hierarchyLen=hierarchyOrderList.length;
    }

    @Override
    public int vote(Authentication authentication, List<String> roles) {
        int userHierarchyIndex=findHierarchyIndex((String) authentication.getCredentials());
        if(isNonExistent(userHierarchyIndex)){
            return ACCESS_DENIED;
        }
        
        for(String role:roles){
            int roleHierarchyIndex = findHierarchyIndex(role);
            if(isNonExistent(roleHierarchyIndex)){
                continue;
            }
            if(userHierarchyIndex<=roleHierarchyIndex){
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_DENIED;
    }

    private int findHierarchyIndex(String role) {
        String[] hierarchyOrderList = hierarchy.split(">");
        for(int i=0;i<hierarchyOrderList.length;i++){
            if(hierarchyOrderList[i].equals(role)){
                return i;
            }
        }
        return hierarchyOrderList.length;
    }

    private boolean isNonExistent(int hierarchyIndex) {
        return hierarchyIndex ==hierarchyLen;
    }
}
