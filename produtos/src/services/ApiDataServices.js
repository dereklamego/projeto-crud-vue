import http from "./api";

class ApiDataService {
  getAll() {
    return http.get("/");
  }

  create(data) {
    return http.post("/", data);
  }

  
  update(id, data) {

    return http.put(`/${id}`, data);
    
  }

  delete(id) {
    return http.delete(`/${id}`);
  }

  deleteAll() {
    return http.delete("/");
  }

  findByName(Name) {
    return http.get(`/${Name}`);
  }
}

export default new ApiDataService();