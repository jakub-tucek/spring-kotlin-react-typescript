import * as React from 'react';

import styled from 'styled-components';

const Title = styled.h1`
  color: red;
`;

interface Props {
    children: any
}

const BlueTitle = (props: Props) => (
    <Title>
        {props.children}
    </Title>
);

export default BlueTitle;