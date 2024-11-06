import http from "../http-common"; 

class HostResponseService {
  getAllHostResponses(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/hostResponse/hostResponses`, searchDTO);
  }

  get(hostResponseId) {
    return this.getRequest(`/hostResponse/${hostResponseId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/hostResponse?field=${matchData}`, null);
  }

  addHostResponse(data) {
    return http.post("/hostResponse/addHostResponse", data);
  }

  update(data) {
  	return http.post("/hostResponse/updateHostResponse", data);
  }
  
  uploadImage(data,hostResponseId) {
  	return http.postForm("/hostResponse/uploadImage/"+hostResponseId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new HostResponseService();
