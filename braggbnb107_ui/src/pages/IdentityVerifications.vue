<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <identityVerification-table
            v-if="identityVerifications && identityVerifications.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:identityVerifications="identityVerifications"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-identity-verifications="getAllIdentityVerifications"
             >

            </identityVerification-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import IdentityVerificationTable from "@/components/IdentityVerificationTable";
import IdentityVerificationService from "../services/IdentityVerificationService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    IdentityVerificationTable,
  },
  data() {
    return {
      identityVerifications: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllIdentityVerifications(sortBy='identityVerificationId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await IdentityVerificationService.getAllIdentityVerifications(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.identityVerifications.length) {
					this.identityVerifications = response.data.identityVerifications;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching identityVerifications:", error);
        }
        
      } catch (error) {
        console.error("Error fetching identityVerification details:", error);
      }
    },
  },
  mounted() {
    this.getAllIdentityVerifications();
  },
  created() {
    this.$root.$on('searchQueryForIdentityVerificationsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllIdentityVerifications();
    })
  }
};
</script>
<style></style>
