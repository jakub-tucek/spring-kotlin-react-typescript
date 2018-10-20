import * as React from 'react'
import {render} from 'react-testing-library'

import PageOneContainer from '../PageOneContainer'

/**
 * Just a simple integration tests
 */
test('PageOneContainer Component renders with toggle switch', () => {
    const wrap = render(<PageOneContainer/>);

    expect(wrap.getByTestId('toggle-container')).toBeTruthy()
});