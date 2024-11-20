<template>

    <v-data-table
        :headers="headers"
        :items="retrieveOrderList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'RetrieveOrderListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            retrieveOrderList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/retrieveOrderLists'))

            temp.data._embedded.retrieveOrderLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.retrieveOrderList = temp.data._embedded.retrieveOrderLists;
        },
        methods: {
        }
    }
</script>

