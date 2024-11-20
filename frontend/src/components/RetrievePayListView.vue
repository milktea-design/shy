<template>

    <v-data-table
        :headers="headers"
        :items="retrievePayList"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'RetrievePayListView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            retrievePayList : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/retrievePayLists'))

            temp.data._embedded.retrievePayLists.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.retrievePayList = temp.data._embedded.retrievePayLists;
        },
        methods: {
        }
    }
</script>

