<template>

    <v-data-table
        :headers="headers"
        :items="retrieveBill"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'RetrieveBillView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            retrieveBill : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/retrieveBills'))

            temp.data._embedded.retrieveBills.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.retrieveBill = temp.data._embedded.retrieveBills;
        },
        methods: {
        }
    }
</script>

