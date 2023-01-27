import api from './api'

class UserService {

    static login(userObj){
        return api.post(`/user/users/login`, userObj);
    }
    static register(userObj){
        return api.post(`/user/users/register`, userObj);
    }

    static createUser(user){
        return api.post(`/users`, user);
    }

    static getUser(userId){
        return api.get(`/users/${userId}`);
    }

    static updateUser(userId, user){
        return api.put(`/users/${userId}`, user);
    }
    
    static deleteUser(userId){
        return api.delete(`/users/${userId}`);
    }
}

export default UserService;
