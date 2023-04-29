import React, { createContext, useContext, useEffect, useState } from "react";
import Cookies from "js-cookie";
import axios from "axios";

import LoaderIcon from "../components/Loader";

interface  User {
    firstName: string,
    lastName: string,
    handle: string,
    email: string,
    role: string,
    backgroundPicture: string
}


export const authContext = createContext({});

const AuthProvider = ({ children }: any ) => {
    const [user, setUser] = useState<User | null>(null);
    const [loading, setLoading] = useState(true);

    async function getUserFromCookies() {
        const token = Cookies.get("token");
        if (token) {
            const response = await axios.get("http://localhost:8080/api/v1/auth/", {
                headers: {
                    'Authorization': token
                }
            })
            const User: User = await response.data;
            if (User?.role) setUser(User);
        }
        setLoading(false);
    }

    useEffect(() => {
        getUserFromCookies();
    }, []);

    return (
        <authContext.Provider
            value={{ isLoggedIn: !!user, user, loading, getUserFromCookies }}
        >
            {children}
        </authContext.Provider>
    );
};

// @ts-ignore
export const PrivateRoute = ({ children }) => {
    // @ts-ignore
    const { isLoggedIn, loading }= useContext(authContext);
    if (loading) return <LoaderIcon />;
    else if (!isLoggedIn) return <h3>You need to login</h3>;
    return isLoggedIn && children;
};

export default AuthProvider;