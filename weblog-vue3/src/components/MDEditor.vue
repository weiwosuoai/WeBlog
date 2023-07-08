<template>
    <div id="editor">
        <mavonEditor fontSize="18px" v-model="myContent"  @change="mdChange" style="height: 100%;"></mavonEditor>
    </div>
</template>
<script>
// Local Registration
import { mavonEditor } from 'mavon-editor'
import { reactive, toRefs } from 'vue'
import 'mavon-editor/dist/css/index.css'
export default {
    name: 'editor',
    components: {
        mavonEditor,
        // or 'mavon-editor': mavonEditor
    },
    props: ['content'],
    setup (props, { emit }) {
        const state = reactive({
            myContent: '',
        })

        const mdChange = () => {
            console.log('内容 change:' + state.myContent)
            emit('event', state.myContent)
        }

        return {
            ...toRefs(state),
            mdChange
        }
    }
}


</script>
<style>
#editor {
    margin: auto;
    width: 100%;
    height: 580px;
}
</style>