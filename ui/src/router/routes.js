
const routes = [
  {
    path: '/',
    component: () => import('layouts/main.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') },
      { path: 'exercise', component: () => import('pages/Exercise.vue') },
      { path: 'settings', component: () => import('pages/Settings.vue') },
      { path: 'history', component: () => import('pages/History.vue') }
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}

export default routes
