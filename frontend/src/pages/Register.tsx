"use client"

import React from "react";
import { useForm } from '@mantine/form';
import {Box, Button, Checkbox, Group, TextInput, Select} from "@mantine/core";
import toast, { Toaster } from 'react-hot-toast';
import axios from "axios";
import Cookies from "js-cookie";

const Register: React.FC = (): JSX.Element => {
    interface FormValues {
        firstName: string;
        lastName: string;
        role: string,
        password: string;
        email: string;
        termsOfService: boolean;
    }


    const form: any = useForm<FormValues>({
        // @ts-ignore
        initialValues: {
            firstName: '',
            lastName: '',
            role: '',
            password: '',
            email: '',
            termsOfService: false,
        },

        validate: {
            firstName: (value: string) => (!value || value.length < 2 ? 'First name must have at least 2 letters' : null),
            lastName: (value: string) => (!value || value.length < 2 ? 'last name must have at least 2 letters' : null),
            password: (value: string) => (!value || value.length < 8 ? 'password must be at least 8 characters long' : null),
            email: (value: string) => (!value || /^\S+@\S+$/.test(value) ? null : 'Invalid email'),
            termsOfService: (value: boolean) => (!value ? 'you have to agree to the terms of service' : null),
        },
    });

        const signIn = async () => {
            const response = await axios.post("http://localhost:8080/api/v1/auth/register", {
                firstName: form.values.firstName,
                lastName: form.values.lastName,
                role: form.values.role,
                email: form.values.email,
                password: form.values.password,
                backgroundPicture: form.values.backgroundPicture
            })
            if (response?.data?.errors) {
                // @ts-ignore
                response.data.errors.forEach(error => {
                    toast.error(error);
                });
                return console.log(response.data.errors);
            }
            Cookies.set("token", `Bearer ${response.data.token}`);
            toast.success("You have registered successfully!");
            return window.location.href = '/home';
        }

    return (
        <div className="register-container">
            <h1>Create your account {process.env.MY}</h1>
            <Box   sx={(theme) => ({
                textAlign: 'left',
            })} maw={300} mx="auto">
                <form onSubmit={form.onSubmit(signIn)}>
                    <TextInput
                        withAsterisk
                        label="First Name"
                        placeholder="your first name"
                        {...form.getInputProps('firstName')}
                    />
                    <TextInput
                        withAsterisk
                        label="Last Name"
                        placeholder="your last name"
                        {...form.getInputProps('lastName')}
                    />
                    <Select
                        label="your role in the app would be?"
                        placeholder="Pick a role"
                        {...form.getInputProps('role')}
                        data={[
                            { value: 'STUDENT', label: 'student' },
                            { value: 'TEACHER', label: 'teacher' }
                        ]}
                    />
                    <TextInput
                        withAsterisk
                        label="Email"
                        placeholder="your@email.com"
                        {...form.getInputProps('email')}
                    />
                    <TextInput
                        withAsterisk
                        label="password"
                        placeholder="choose a password"
                        {...form.getInputProps('password')}
                    />
                    <Checkbox
                        mt="md"
                        label="I agree the the terms of service"
                        {...form.getInputProps('termsOfService', { type: 'checkbox' })}
                    />

                    <Group position="right" mt="md">
                        <Button type="submit">Submit</Button>
                    </Group>
                </form>
            </Box>
            <Toaster />
        </div>
    )
}

export default Register;