import React from "react";
import { useForm } from '@mantine/form';
import {Box, Button, Checkbox, Group, TextInput} from "@mantine/core";

const Register: React.FC = (): JSX.Element => {
    interface FormValues {
        f_name: string;
        l_name: string;
        password: string;
        email: string;
        termsOfService: boolean;
    }

    const form: any = useForm<FormValues>({
        initialValues: {
            f_name: '',
            l_name: '',
            password: '',
            email: '',
            termsOfService: false,
        },

        validate: {
            f_name: (value: string) => (!value || value.length < 2 ? 'First name must have at least 2 letters' : null),
            l_name: (value: string) => (!value || value.length < 2 ? 'last name must have at least 2 letters' : null),
            password: (value: string) => (!value || value.length < 8 ? 'password must be at least 8 characters long' : null),
            email: (value: string) => (!value || /^\S+@\S+$/.test(value) ? null : 'Invalid email'),
            termsOfService: (value: boolean) => (!value ? 'you have to agree to the terms of service' : null),
        },
    });
    return (
        <div className="register-container">
            <h1>Create your account {process.env.MY}</h1>
            <Box   sx={(theme) => ({
                textAlign: 'left',
            })} maw={300} mx="auto">
                <form onSubmit={form.onSubmit((values: any) => console.log(values))}>
                    <TextInput
                        withAsterisk
                        label="First Name"
                        placeholder="your first name"
                        {...form.getInputProps('f_name')}
                    />
                    <TextInput
                        withAsterisk
                        label="Last Name"
                        placeholder="your last name"
                        {...form.getInputProps('l_name')}
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
        </div>
    )
}

export default Register;