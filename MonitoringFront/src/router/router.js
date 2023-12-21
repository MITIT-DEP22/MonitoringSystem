//the file for page`s routing.
//for add new page just add element in 'routes' array, add path and import component

import Main from "@/pages/Main";
import {createRouter, createWebHistory} from "vue-router";
import Classroom from "@/pages/Classroom"
import History from "@/pages/History";

const routes = [
    {
        path: '',
        component: Main
    },
    {
        path: '/classroom/:classroomNumber',
        component: Classroom
    },
    {
        path: '/history',
        component: History
    },
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;