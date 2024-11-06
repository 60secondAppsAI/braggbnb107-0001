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
            <hostResponse-table
            v-if="hostResponses && hostResponses.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:hostResponses="hostResponses"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-host-responses="getAllHostResponses"
             >

            </hostResponse-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import HostResponseTable from "@/components/HostResponseTable";
import HostResponseService from "../services/HostResponseService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    HostResponseTable,
  },
  data() {
    return {
      hostResponses: [],
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
    async getAllHostResponses(sortBy='hostResponseId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await HostResponseService.getAllHostResponses(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.hostResponses.length) {
					this.hostResponses = response.data.hostResponses;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching hostResponses:", error);
        }
        
      } catch (error) {
        console.error("Error fetching hostResponse details:", error);
      }
    },
  },
  mounted() {
    this.getAllHostResponses();
  },
  created() {
    this.$root.$on('searchQueryForHostResponsesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllHostResponses();
    })
  }
};
</script>
<style></style>
