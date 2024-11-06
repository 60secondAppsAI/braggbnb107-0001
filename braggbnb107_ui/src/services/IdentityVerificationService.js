import http from "../http-common"; 

class IdentityVerificationService {
  getAllIdentityVerifications(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/identityVerification/identityVerifications`, searchDTO);
  }

  get(identityVerificationId) {
    return this.getRequest(`/identityVerification/${identityVerificationId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/identityVerification?field=${matchData}`, null);
  }

  addIdentityVerification(data) {
    return http.post("/identityVerification/addIdentityVerification", data);
  }

  update(data) {
  	return http.post("/identityVerification/updateIdentityVerification", data);
  }
  
  uploadImage(data,identityVerificationId) {
  	return http.postForm("/identityVerification/uploadImage/"+identityVerificationId, data);
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

export default new IdentityVerificationService();
