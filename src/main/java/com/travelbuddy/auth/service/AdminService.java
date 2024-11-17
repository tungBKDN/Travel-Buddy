package com.travelbuddy.auth.service;

public interface AdminService {
    int getAdminIdByEmailOrUsername(String emailOrUsername);
}
