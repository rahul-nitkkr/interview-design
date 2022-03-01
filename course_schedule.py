def get_halfway_course(self, courses):
    
    # Create a dictionary to store the prequisite : course as key:value pair
    dikt = {}
    
    # Iterate over each pair and populate the dictioary
    for course in courses:
        p = course[0]
        c = course[1]
        dikt[p] = c
    
    
    # The starting element will be present in keys but not in values
    diff = set(dikt.keys()) - set(dikt.values()) 
    
    # Get the starting point
    starting_point = list(diff)[0]
    
    # Arrange the courses in order
    result = [starting_point]
    while True:
        key = result[-1]
        value = dikt.get(key)
        if not value:
            break
        result.append(value)

    # Return the required element
    mid = len(result)//2
    if len(result) % 2 == 0:
        return result[mid-1]
    else:
        return result[mid]  
