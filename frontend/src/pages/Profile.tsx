import {redirect} from "react-router-dom";
const Profile = () => {
    const authUser = false;
    if (!authUser) {
        // not logged in so redirect to login page with the return url
        return redirect("/login")
    }

    return (
        <div>
            <h1>Profile Page!!!!!!!!!</h1>
        </div>
    )
}


export default Profile;