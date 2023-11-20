import {useEffect, useState} from 'react';
import MainLayout from '../layouts/MainLayout';
import axios from "axios";
import {toast} from 'react-toastify';
import { useNavigate } from 'react-router-dom';

const POSPage = () => {

    const navigate = useNavigate();

    useEffect(() => {
        if (localStorage.getItem('loggedInEmployee') === null) {
        navigate('/login');
        }
    },[]);
    
    const [products, setProducts] = useState([])
    const [cart, setCart] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [totalAmount, setTotalAmount] = useState(0);

    const fetchProducts = async() => {
        setIsLoading(true);
        
        const result = await axios.get("http://localhost:8080/product");
        setProducts(await result.data);
        setIsLoading(false);
    }

    useEffect(() => {
        fetchProducts();
    },[]);

    const toastOptions = {
        autoClose: 400,
        pauseOnHover: true,
    }

    const addProductToCart = async(product) =>{
        // this console prints after the btn click
        // console.log(product); works fine but isn't adding to cart
        let findProductInCart = await cart.find(i=>{
            return i.id === product.id
        });

        if(findProductInCart){
            let newCart = [];
            let newItem;

            cart.forEach(cartItem => {
                if(cartItem.id === product.id) {
                    newItem = {
                        ...cartItem,
                        quantity: cartItem.quantity + 1,
                        totalAmount: cartItem.price * (cartItem.quantity + 1)
                    }
                    newCart.push(newItem);
                }else {
                    newCart.push(cartItem);
                }
            })
            setCart(newCart);
            toast(`Added ${newItem.name} to cart`, toastOptions)

        }else{
            let addingProduct = {
                ...product,
                'quantity': 1,
                'totalAmount': product.price,
            }
            setCart([...cart, addingProduct])
            toast(`Added ${product.name} to cart`, toastOptions)

        }
    }

    const removeProduct = async(product) =>{
        const newCart = cart.filter(cartItem => cartItem.id !== product.id);
        setCart(newCart);
    }

    // this will handle submitting order 
    // post request to order
    const handleSubmit = () => {
        console.log("order submitted")

        // post multiple orderDetails 
    }

    

    useEffect(() => {
        let newTotalAmount = 0;
        cart.forEach(icart => {
            newTotalAmount = newTotalAmount + parseFloat(icart.price * icart.quantity);
        })
        setTotalAmount(newTotalAmount);
    },[cart])

    return (
        <MainLayout> 
            <div className='row'>
                <div className='col-lg-8'>
                    <div className='row'>
                    {products.map((product, key) => (
                        <div key={key} className='col-lg-4 mb-4'>
                            <div className='pos-item px-3 text-center border' 
                            onClick={() => addProductToCart(product)}>
                                <p>{product.name}</p>
                                {/* <img className='img-fluid' 
                                src={product.img} alt={product.productID} 
                                style={{ height:'200px', width:'200px'}}/> */}
                                <p>${product.price}</p>
                            </div>
                        </div>  
                
                    ))}
                    </div>
                </div>
                <div className='col-lg-4'>
                    <div className='table-responsive bg-dark'>
                        <table className='table table-responsive table-dark table-hover'>
                            <thead>
                                <tr>
                                    <td>#</td>
                                    <td>Name</td>
                                    <td>Price</td>
                                    <td>Qty</td>
                                    <td>Total</td>
                                    <td>Action</td>
                                </tr>
                            </thead>
                            <tbody>
                            { cart ? cart.map((cartProduct, key) => 
                                <tr key={key}>
                                    <td>{cartProduct.id}</td>
                                    <td>{cartProduct.name}</td>
                                    <td>{cartProduct.price}</td>
                                    <td>{cartProduct.quantity}</td>
                                    <td>{cartProduct.totalAmount}</td>
                                    <td>
                                        <button className='btn btn-danger btn-sm' onClick={() => removeProduct(cartProduct)}>Remove</button>
                                    </td>
                                    </tr>)
                                    : 'No Item in Cart'}
                            </tbody>
                        </table>
                        <h2 className='px-2 text-white'>Total Amount: ${totalAmount}</h2>
                    </div>

                    <div className='mt-3'>
                        <button className='btn btn-primary' onClick={handleSubmit}>
                            Submit Order
                        </button>
                    </div>

                </div>
                
            </div>
        </MainLayout>
    );
}

export default POSPage;


