import {useEffect} from "react";
import { createBrowserRouter, RouterProvider, redirect } from "react-router-dom";
import AuthProvider ,{ PrivateRoute } from "./contexts/auth";
import Profile from "./pages/Profile";


import './App.scss';
import Home from "./pages/Home";
import Register from "./pages/Register";

// @ts-ignore
function App(Component) : JSX.Element {

    // @ts-ignore
    useEffect(() => {
        if (Component.requireAuth) {
            // @ts-ignore
            console.log("required auth!!")
            return redirect("/login")
        }
    }, [])

  // @ts-ignore
    // @ts-ignore
    // @ts-ignore
    const router = createBrowserRouter([
      {
          path: "/",
          element: <Home />
      },
      {
          path: "/register",
          element: <Register />
      },
      {
          path: "/profile",
          //@ts-ignore
          element: <Profile />
      }
    ])

  return (
    <div className="App">
      <RouterProvider router={router} />
    </div>
  );
}

export default App;
