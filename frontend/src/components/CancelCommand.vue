<template>
    <v-card outlined>
        <v-card-title>
            Cancel
        </v-card-title>

        <v-card-text>
            <Number label="PayId" v-model="value.payId" :editMode="editMode"/>
            <Number label="BillId" v-model="value.billId" :editMode="editMode"/>
            <String label="UseYm" v-model="value.useYm" :editMode="editMode"/>
            <String label="UserName" v-model="value.userName" :editMode="editMode"/>
            <Money offline label="PayAmt" v-model="value.payAmt" :editMode="editMode" @change="change"/>
            <String label="WorkType" v-model="value.workType" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="primary"
                    text
                    @click="cancel"
            >
                Cancel
            </v-btn>
            
            <v-btn
                    color="primary"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>
   
    export default {
        name: 'CancelCommand',
        components:{},
        props: {},
        data: () => ({
            editMode: true,
            value: {},
        }),
        created() {
            this.value.payId = 0;
            this.value.billId = 0;
            this.value.useYm = '';
            this.value.userName = '';
            this.value.payAmt = {};
            this.value.workType = '';
        },
        watch: {
        },
        methods: {
            cancel() {
                this.$emit('cancel', this.value);
            },
            close() {
                this.$emit('closeDialog');
            },
            change() {
                this.$emit('input', this.value);
            },
        }
    }
</script>

