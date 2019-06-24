import React from "react";

class RestController extends React.Component {
  constructor(props) {
    super(props);
    this.state = { users: [] };
    this.headers = [
      { key: "catId", label: "Cat ID" },
      { key: "catName", label: "Cat Name" },
      { key: "catPath", label: "Cat Path" },
      { key: "level", label: "Level" },
      { key: "parentCatId", label: "Parent Id" }
    ];
  }

  componentDidMount() {
    fetch("http://localhost:9090/api/category")
      .then(response => {
        return response.json();
      })
      .then(result => {
        this.setState({
          users: result
        });
      });
  }
  render() {
    return (
      <table>
        <thead>
          <tr>
            {this.headers.map(function(h) {
              return <th key={h.key}>{h.label}</th>;
            })}
          </tr>
        </thead>
        <tbody>
          {this.state.users.map(function(item, key) {
            return (
              <tr key={key}>
                <td>{item.catId}</td>
                <td>{item.catName}</td>
                <td>{item.catPath}</td>
                <td>{item.level}</td>
                <td>{item.parentCatId}</td>
              </tr>
            );
          })}
        </tbody>
      </table>
    );
  }
}

export default RestController;
