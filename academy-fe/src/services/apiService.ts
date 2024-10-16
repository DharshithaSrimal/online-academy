// src/services/apiService.ts
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8082/api'; // Academy micro service

// Function to fetch all courses
export const getCourses = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/courses`);
        return response.data;
    } catch (error) {
        console.error('Error fetching courses:', error);
        throw error;
    }
};
